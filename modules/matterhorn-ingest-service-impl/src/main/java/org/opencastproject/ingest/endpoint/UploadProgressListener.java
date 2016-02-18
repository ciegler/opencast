/**
 * Licensed to The Apereo Foundation under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 *
 * The Apereo Foundation licenses this file to you under the Educational
 * Community License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at:
 *
 *   http://opensource.org/licenses/ecl2.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package org.opencastproject.ingest.endpoint;

import org.apache.commons.fileupload.ProgressListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;

/**
 *
 */
public class UploadProgressListener implements ProgressListener {
  private static final Logger logger = LoggerFactory.getLogger(UploadProgressListener.class);

  // ProgressListeners can happen to be called with a high frequency, depending
  // on the ServeletEngine (see fileupload doc). So we save the job object only
  // after every X Kb that have arrived to avoid doing to many persist operations.
  private static final int SAVE_INTERVAL = 50 * 1024;

  private UploadJob job;
  @SuppressWarnings("unused")
  private EntityManagerFactory emf;
  private long lastSaved = 0L;

  public UploadProgressListener(UploadJob job, EntityManagerFactory emf) {
    this.job = job;
    this.emf = emf;
  }

  /**
   * Called by ServeletFileUpload on upload progress. Updates the job object. Persists the job object on upload
   * start/complete and after every X Kb that have arrived.
   *
   * @param rec
   * @param total
   * @param i
   */
  @Override
  public void update(long rec, long total, int i) {
    job.setBytesTotal(total);
    job.setBytesReceived(rec);
    if ((rec == 0L) || // persist job on upload start
            (rec - lastSaved >= SAVE_INTERVAL) || // after X Kb
            (rec == total)) { // on upload complete
      // logger.info("trying to save Progress object - total: " + job.getBytesTotal() + " rec: " +
      // job.getBytesReceived());
      /*
       * EntityManager em = emf.createEntityManager(); try { em.persist(job); } catch (Exception e) {
       * logger.info(e.getMessage()); } finally { em.close(); }
       */
      lastSaved = rec;
    }
  }
}
